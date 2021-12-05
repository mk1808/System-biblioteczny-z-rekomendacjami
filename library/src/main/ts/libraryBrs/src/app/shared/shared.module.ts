import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPagesModule } from '../main-pages/main-pages.module';
import { LayoutComponent } from './layout/layout.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { IvyCarouselModule } from 'angular-responsive-carousel';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    MainPagesModule,
    IvyCarouselModule,
    RouterModule
  ],
  exports: [
    LayoutComponent,
    IvyCarouselModule

  ]
})
export class SharedModule { }
