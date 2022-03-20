import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookSearchComponent } from './book-search/book-search.component';
import { BookRoutingModule } from './book-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CarouselElementComponent } from '../main-pages/main/carousel/carousel-element/carousel-element.component';
import { BookSearchElementComponent } from './book-search/book-search-element/book-search-element.component';
import { MyBooksComponent } from './my-books/my-books.component';
import { NgbAlertModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BookDetailsComponent } from './book-details/book-details.component';
import { BookAvailabilityComponent } from './book-details/book-availability/book-availability.component';
import { CoreModule } from '../core/core.module';
import { CommentComponent } from './book-details/comment/comment.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReservationModalComponent } from './book-details/reservation-modal/reservation-modal.component';
//import { BookOpinionComponent } from '../auth/book-opinion/book-opinion.component';



@NgModule({
  declarations: [
    BookSearchComponent,
    BookSearchElementComponent,
    MyBooksComponent,
    BookDetailsComponent,
    BookAvailabilityComponent,
    CommentComponent,
    ReservationModalComponent,
  //  BookOpinionComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    SharedModule,
    CoreModule,
    NgbModule,
    FormsModule, 
    ReactiveFormsModule,
    NgbAlertModule
  ],
  providers:[
    CoreModule,NgbAlertModule
  ],

})
export class BookModule { }
