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
import { CheckboxComponent } from './elements/checkbox/checkbox.component';
import { MultiSelectComponent } from './elements/multi-select/multi-select.component';
import { NotificationBarComponent } from './elements/notification-bar/notification-bar.component';
import { TwoColumnsContentComponent } from './layout/main-content/two-columns-content/two-columns-content.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    FooterComponent,
    MainContentComponent,
    InputComponent,
    ButtonComponent,
    SelectComponent,
    CheckboxComponent,
    MultiSelectComponent,
    NotificationBarComponent,
    TwoColumnsContentComponent
  ],
  imports: [
    CommonModule,
    //MainPagesModule,
    RouterModule,
    I18NextModule,
    NgbModule
  ],
  exports: [
    LayoutComponent,
    MainContentComponent,
    InputComponent,
    ButtonComponent,
    SelectComponent,
    MultiSelectComponent,
    CheckboxComponent,
    NotificationBarComponent,
    TwoColumnsContentComponent,
    I18NextModule

  ]
})
export class SharedModule { }
