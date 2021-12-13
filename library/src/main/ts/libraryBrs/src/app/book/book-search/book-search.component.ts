import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.scss']
})
export class BookSearchComponent implements OnInit {
  title:any = "booksSearch";
  iconClass="search"
  text="search"
  label = "Tytuł"
  btnClass="full"
  no=0;
  book={id:0, photo:'https://picsum.photos/200/300', title:'Lorem ipsum dolor', author:'Dolores Gibson'}
  books=[this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook() ];
  

  constructor() { }

  ngOnInit(): void {
  }

  getBook(){
    this.no++;
    this.book.id=this.no;
    return this.book;
  }
}
