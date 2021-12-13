import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookSearchComponent } from './book-search/book-search.component';
import { BookRoutingModule } from './book-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CarouselElementComponent } from '../main-pages/main/carousel/carousel-element/carousel-element.component';
import { BookSearchElementComponent } from './book-search/book-search-element/book-search-element.component';



@NgModule({
  declarations: [
    BookSearchComponent,
    BookSearchElementComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    SharedModule
  ]
})
export class BookModule { }
