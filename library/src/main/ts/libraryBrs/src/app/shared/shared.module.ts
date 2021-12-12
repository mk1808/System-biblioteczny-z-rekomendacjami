import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPagesModule } from '../main-pages/main-pages.module';
import { LayoutComponent } from './layout/layout.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { RouterModule } from '@angular/router';
import { I18NextModule } from 'angular-i18next';
import { MainContentComponent } from './layout/main-content/main-content.component';



@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    FooterComponent,
    MainContentComponent
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
    I18NextModule

  ]
})
export class SharedModule { }
