package szwedo.bookhubapp.utils;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import szwedo.bookhubapp.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PaginationBar {
    private final View view;
    private LinearLayout panel;
    private ImageButton previous, next;
    private Node[] nodes;
    private int currentPage;
    private int previousPage;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;

    public PaginationBar(View view) {
        this.view = view;
        initComponents();
    }

    public void setPage(PageHolder<?> page){
        totalPages = page.getTotalPages();
        isFirst = page.isFirst();
        isLast = page.isLast();

        previousPage = currentPage;
        currentPage = page.getCurrentPage();
        update();
    }

    public void setOnPreviousClickListener(View.OnClickListener listener){
        previous.setOnClickListener(listener);
    }

    public void setOnNextClickListener(View.OnClickListener listener){
        next.setOnClickListener(listener);
    }

    public void setOnPageClickListener(View.OnClickListener listener){
        for(Node node: nodes){
            node.getTextView().setOnClickListener(listener);
        }
    }

    public int nextPage(){
        return currentPage + 1;
    }

    public int previousPage(){
        return currentPage - 1;
    }

    public View getView() {
        return view;
    }
    
    private void update(){
        setComponentsVisibility();
        clearPreviousElement();
        if(totalPages >= 5){
            showCurrentPage();
            setPageNumbers();
        }
        else {
            showCurrentPage2();
            setPageNumbers2();
        }
        previous.setClickable(!isFirst);
        next.setClickable(!isLast);
    }

    private void setPageNumbers2() {
        for(int i=1; i<4; i++)
            nodes[i].setValue(i + 1);
    }

    private void showCurrentPage2() {
        emphasiseElement(nodes[currentPage]);
    }

    private void setPageNumbers() {
        if (currentPage <= 2){
            nodes[1].setValue(2);
            nodes[2].setValue(3);
            nodes[3].setValue(4);
        }
        else if(currentPage < totalPages-2){
            // in the middle
            nodes[1].setValue(currentPage);     // one page back
            nodes[2].setValue(currentPage + 1); // current page (+1 because we count from 0)
            nodes[3].setValue(currentPage + 2); // one page forward
        }
        else if (currentPage == totalPages-1){
            nodes[1].setValue(totalPages - 3);
            nodes[2].setValue(totalPages-2);
            nodes[3].setValue(totalPages-1);
        }
        nodes[4].setValue(totalPages);
    }

    private void showCurrentPage() {
        if(currentPage == 0){
            emphasiseElement(nodes[0]);
        }
        else if (currentPage == 1){
            emphasiseElement(nodes[1]);
        }
        else if(currentPage == totalPages - 2){
            emphasiseElement(nodes[3]);
        }
        else if(currentPage == totalPages-1){
            emphasiseElement(nodes[4]);
        }
        else{
            emphasiseElement(nodes[2]);
        }
    }

    private void clearPreviousElement() {
        for(Node node: nodes){
            if(node.isActive()){
                clearElement(node);
                return;
            }
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void emphasiseElement(Node node){
        node.getTextView().setBackground(view.getContext().getDrawable(R.drawable.current_page));
        node.getTextView().setTextColor(view.getResources().getColor(R.color.colorWhite));
        node.setActive(true);
    }

    private void clearElement(Node node){
        node.getTextView().setBackground(null);
        node.getTextView().setTextColor(view.getResources().getColor(R.color.colorSecondaryDark));
        node.setActive(false);
    }

    private void setComponentsVisibility(){
        if(totalPages < 5){
            if(totalPages <= 1) panel.setVisibility(GONE);
            else {
                panel.setVisibility(VISIBLE);
                for(int i = 0; i<5; i++){
                    if(i<totalPages) nodes[i].setVisibility(VISIBLE);
                    else nodes[i].setVisibility(GONE);
                }
            }
        }
        else{
            for(int i = 0; i<5; i++){
                nodes[i].setVisibility(VISIBLE);
                panel.setVisibility(VISIBLE);
            }
        }
    }

    private void initComponents() {
        previous = view.findViewById(R.id.pageBar_btn_previous);
        next = view.findViewById(R.id.pageBar_btn_next);

        nodes = new Node[5];
        nodes[0] = new Node((TextView) view.findViewById(R.id.pageBar_text_first));
        nodes[1] = new Node((TextView)view.findViewById(R.id.pageBar_text_middle1));
        nodes[2] = new Node((TextView)view.findViewById(R.id.pageBar_text_middle2));
        nodes[3] = new Node((TextView)view.findViewById(R.id.pageBar_text_middle3));
        nodes[4] = new Node((TextView) view.findViewById(R.id.pageBar_text_last));
        panel = view.findViewById(R.id.pageBar_linear_parent);
    }

    private class Node{
        private final TextView textView;
        private boolean active;

        public Node(TextView textView) {
            this.textView = textView;
            active = false;
        }

        public void setVisibility(int n){
            textView.setVisibility(n);
        }

        public TextView getTextView() {
            return textView;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setValue(int value) {
            textView.setText(String.valueOf(value));
        }
    }
}
