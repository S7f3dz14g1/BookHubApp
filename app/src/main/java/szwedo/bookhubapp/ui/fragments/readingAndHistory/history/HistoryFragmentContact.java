package szwedo.bookhubapp.ui.fragments.readingAndHistory.history;

import java.util.List;

import szwedo.bookhubapp.models.HistoryBookModel;

public interface HistoryFragmentContact {
    interface View {
        void setList(List<HistoryBookModel> books);

        void setDarkList();

        void setEmptyLayout();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetDialog();

        void onRefresh();
    }

    interface Presenter {
        void setList();

        void setPaginationComponent(android.view.View view);
    }
}
