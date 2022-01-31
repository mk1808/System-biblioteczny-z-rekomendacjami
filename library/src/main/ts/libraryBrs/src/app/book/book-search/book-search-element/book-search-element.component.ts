import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { setOptions } from 'leaflet';
import { Author, Book } from 'src/app/core/services/rest/api/api';

@Component({
  selector: 'app-book-search-element',
  templateUrl: './book-search-element.component.html',
  styleUrls: ['./book-search-element.component.scss']
})
export class BookSearchElementComponent implements OnInit, OnChanges {
  bookToDisplay: Book = {};
  @Input() book: Book = {};
  constructor(private route: ActivatedRoute, private router: Router) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.bookToDisplay = this.book;
    console.log(this.book)
  }

  ngOnInit(): void {
    //this.bookToDisplay=this.book;
  }


  getAuthorDisplayedValue(options: Author[] | undefined) {
    let displayed = ""
    if (options){
      options.forEach(option => { displayed += `${option.name} ${option.surname}, ` }) ;
      displayed = displayed.substring(0, displayed.length - 2);
    }
      
   
    return displayed;

  }

  onClick(){
    this.router.navigate(['/books/details/'+this.book.id]);
  }

}
