import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookSearchComponent } from './book-search/book-search.component';
import { BookRoutingModule } from './book-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CarouselElementComponent } from '../main-pages/main/carousel/carousel-element/carousel-element.component';
import { BookSearchElementComponent } from './book-search/book-search-element/book-search-element.component';
import { MyBooksComponent } from './my-books/my-books.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BookDetailsComponent } from './book-details/book-details.component';
import { BookAvailabilityComponent } from './book-details/book-availability/book-availability.component';
import { CoreModule } from '../core/core.module';
//import { BookOpinionComponent } from '../auth/book-opinion/book-opinion.component';



@NgModule({
  declarations: [
    BookSearchComponent,
    BookSearchElementComponent,
    MyBooksComponent,
    BookDetailsComponent,
    BookAvailabilityComponent,
  //  BookOpinionComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    SharedModule,
    CoreModule,
    NgbModule
  ],
  providers:[
    CoreModule
  ],

})
export class BookModule { }
