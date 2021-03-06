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

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import net.isanakamishiro.boxbreathing.presentation.box.BoxModule;
import net.isanakamishiro.boxbreathing.presentation.circle.CircleModule;
import net.isanakamishiro.boxbreathing.presentation.home.HomeModule;
import net.isanakamishiro.boxbreathing.presentation.kizuna.KizunaModule;
import net.isanakamishiro.boxbreathing.presentation.mirai.MiraiModule;

public class ApplicationModule extends AbstractPresenterModule {

    @Override
    protected void configure() {

        install(new HomeModule());
        install(new CircleModule());
        install(new BoxModule());
        install(new KizunaModule());
        install(new MiraiModule());

//        install(new WebpModule());
//        install(new AccountModule());
//        install(new PushCompanionModule());
        bind(StyleConfigurator.class).asEagerSingleton();

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
