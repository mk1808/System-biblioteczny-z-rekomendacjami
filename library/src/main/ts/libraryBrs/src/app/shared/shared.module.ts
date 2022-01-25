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
import { NgbAccordion, NgbAccordionModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from './elements/modal/modal.component';
import { NgbActiveModal, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { ExampleModalComponent } from './elements/modal/example-modal/example-modal.component';
import { DropdownComponent } from './elements/dropdown/dropdown.component';
import { RatingComponent } from './elements/rating/rating.component';
import { TextareaComponent } from './elements/textarea/textarea.component';



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
    TwoColumnsContentComponent,
    ModalComponent,
    ExampleModalComponent,
    DropdownComponent,
    RatingComponent,
    TextareaComponent
  ],
  imports: [
    CommonModule,
    //MainPagesModule,
    RouterModule,
    I18NextModule,
    NgbModule,
    NgbAccordionModule,
    NgbModalModule
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
    ModalComponent,
    ExampleModalComponent,
    DropdownComponent,
    RatingComponent,
    TextareaComponent,
    NgbModalModule,
    NgbModule,
    I18NextModule

  ]
})
export class SharedModule { }
