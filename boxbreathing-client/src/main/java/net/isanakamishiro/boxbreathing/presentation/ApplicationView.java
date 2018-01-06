/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package net.isanakamishiro.boxbreathing.presentation;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.intendia.rxgwt2.user.RxEvents;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialPanel;
import io.reactivex.Observable;

import javax.inject.Inject;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

    interface Binder extends UiBinder<Widget, ApplicationView> {

    }

    @UiField
    MaterialPanel rootPanel;

    @UiField
    MaterialContainer container;

    @UiField
    MaterialButton btnAbout;

    @UiField
    MaterialNavBar navBar;

    @UiField
    MaterialNavBrand navBarBrand;

    @Inject
    ApplicationView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(ApplicationPresenter.SLOT_MAIN, container);

        initStyle();
        initEventHadling();
    }

    private void initStyle() {
    }

    private void initEventHadling() {
        Observable.merge(RxEvents.click(btnAbout), RxEvents.touchEnd(btnAbout))
                .subscribe(e -> Window.open(btnAbout.getHref(), "_blank", ""));
    }

    @Override
    public void setBackgroundColorStyle(Color color) {

        rootPanel.setBackgroundColor(color);
        navBar.setBackgroundColor(color);

        if (Color.WHITE.equals(color)) {
            navBar.getNavMenu().setTextColor(Color.GREY);
            navBarBrand.setTextColor(Color.GREY);
        } else {
            navBar.getNavMenu().setTextColor(Color.WHITE);
            navBarBrand.setTextColor(Color.WHITE);
        }
    }


}
