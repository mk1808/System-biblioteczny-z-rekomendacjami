import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPagesModule } from '../main-pages/main-pages.module';
import { LayoutComponent } from './layout/layout.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { RouterModule } from '@angular/router';
import { I18NextModule } from 'angular-i18next';
import { MainContentComponent } from './layout/main-content/main-content.component';
import { InputComponent } from './elements/input/input.component';
import { ButtonComponent } from './elements/button/button.component';
import { SelectComponent } from './elements/select/select.component';



@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    FooterComponent,
    MainContentComponent,
    InputComponent,
    ButtonComponent,
    SelectComponent
  ],
  imports: [
    CommonModule,
    //MainPagesModule,
    RouterModule,
    I18NextModule
  ],
  exports: [
    LayoutComponent,
    MainContentComponent,
    InputComponent,
    ButtonComponent,
    SelectComponent,
    I18NextModule

  ]
})
export class SharedModule { }
