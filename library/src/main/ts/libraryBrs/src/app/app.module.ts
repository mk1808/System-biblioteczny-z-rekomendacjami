import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { I18N_PROVIDERS } from './shared/i18n';
import { I18NextModule } from 'angular-i18next';
import { MainPagesModule } from './main-pages/main-pages.module';
import { TestComponent } from './test/test.component';
import localePl from '@angular/common/locales/pl';
import { registerLocaleData } from '@angular/common';
registerLocaleData(localePl);

@NgModule({
  declarations: [
    AppComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    I18NextModule.forRoot(),
    //MainPagesModule
  ],
  providers: [
    I18N_PROVIDERS
  ],
  exports:[
    AppRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
