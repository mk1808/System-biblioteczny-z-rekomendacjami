import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookDetailsComponent } from './book-details/book-details.component';
import { BookSearchComponent } from './book-search/book-search.component';
import { MyBooksComponent } from './my-books/my-books.component';




const routes: Routes = [

  { path: 'search',  component: BookSearchComponent },
  { path: 'myLists',  component: MyBooksComponent },
  { path: 'details/:id',  component: BookDetailsComponent },
  { path: '**',  component: BookSearchComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class BookRoutingModule { }
