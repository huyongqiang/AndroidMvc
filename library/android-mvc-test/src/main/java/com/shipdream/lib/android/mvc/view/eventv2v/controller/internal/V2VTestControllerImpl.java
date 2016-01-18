package com.shipdream.lib.android.mvc.view.eventv2v.controller.internal;

import com.shipdream.lib.android.mvc.controller.internal.BaseControllerImpl;
import com.shipdream.lib.android.mvc.view.eventv2v.controller.V2VTestController;

public class V2VTestControllerImpl extends BaseControllerImpl implements V2VTestController{
    @Override
    protected Class getModelClassType() {
        return null;
    }

    @Override
    public void updateDialogButton(Object sender, String text) {
        postToViews(new EventC2V.OnButtonUpdated(sender, text));
    }
}
