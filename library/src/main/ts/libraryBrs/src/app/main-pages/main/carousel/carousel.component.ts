import { Component, OnInit } from '@angular/core';
import { FormatterService } from 'src/app/core/services/formatter.service';
import { Book } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  number=10;
  no=0;
  properties = { height: 500, cells: 5, areArrowsOutside: true };
  book={id:0, photo:'https://picsum.photos/200/300', title:'Lorem ipsum dolor', author:'Dolores Gibson'}
  books:Book[]=[];
  authors:string[]=[];
  
  constructor(private booksService: BooksService, private formatterService: FormatterService) { }

  ngOnInit(): void {
    this.getBooks();
  }
  getBook(){
    this.no++;
    this.book.id=this.no;
    return this.book;
  }

  getBooks(){
    this.booksService.getNewest(this.number).subscribe(resp=>{
      this.books = resp.content;
      let i = 0;
      this.books.forEach((book)=>{
        this.authors.push(this.formatAuthors(book.authors));
      })
      debugger;
    })
  }

  formatAuthors(list: Object[] | undefined) {
    if (list) { return this.formatterService.displayList(list); }
    return "-"
  }
}