package nerianachum.com.happybirthday;

import android.os.Bundle;
import android.view.View;

/**
 * MVP view interface.
 *
 * MVP view is used for presenting information to the user. MVP view will usually wrap one or more
 * Android View's while adding logic for communication with MVP Presenter (Activity, Fragment).
 */
public interface MvpView {

    /**
     * Get the root Android View which is used internally by this MVP View for presenting data
     * to the user.
     * The returned Android View might be used by an MVP Presenter in order to query or alter the
     * properties of either the root Android View itself, or any of its child Android View's.
     * @return root Android View of this MVP View
     */
    View getRootView();

    /**
     * This method aggregates all the information about the state of this MVP View into Bundle
     * object. The keys in the returned Bundle must be provided as public constants inside the
     * interfaces of concrete MVP views.
     * The main use case for this method is exporting the state of editable Android Views underlying
     * the MVP view. This information can be used by MVP controller for e.g. processing user's
     * input or saving view's state during lifecycle events.
     * @return Bundle containing the state of this MVP View, or null if the view has no state
     */
    Bundle getViewState();

}