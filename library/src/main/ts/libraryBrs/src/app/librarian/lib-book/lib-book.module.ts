import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddBookComponent } from './add-book/add-book.component';
import { LibBookRoutingModule } from './lib-book-routing.module';
import { LibBookDetailsComponent } from './lib-book-details/lib-book-details.component';
import { LibBookSearchComponent } from './lib-book-search/lib-book-search.component';
import { AddBookCopyComponent } from './add-book/add-book-copy/add-book-copy.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { LibBookSearchElementComponent } from './lib-book-search/lib-book-search-element/lib-book-search-element.component';



@NgModule({
  declarations: [
    AddBookComponent,
    LibBookDetailsComponent,
    LibBookSearchComponent,
    AddBookCopyComponent,
    LibBookSearchElementComponent
  ],
  imports: [
    CommonModule,
    LibBookRoutingModule,
    SharedModule
  ]
})
export class LibBookModule { }
