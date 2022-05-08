import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { FormatterService } from 'src/app/core/services/formatter.service';
import { Author, Book, BookAvailability } from 'src/app/core/services/rest/api/api';

@Component({
  selector: 'app-lib-book-search-element',
  templateUrl: './lib-book-search-element.component.html',
  styleUrls: ['./lib-book-search-element.component.scss']
})
export class LibBookSearchElementComponent implements OnInit, OnChanges {
  bookToDisplay: Book = {};
  availabilityToDisplay: BookAvailability = {}
  @Input() availability: BookAvailability = {};
  @Input() book: Book = {};
  constructor(private router: Router, private formatterService: FormatterService) { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.bookToDisplay = this.book;
    this.availabilityToDisplay = this.availability;
    console.log(this.book)
    console.log(this.availability)
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

  formatOther(list: Object[] | undefined) {
    if (list) { return this.formatterService.displayListNames(list); }
    return "-";
  }

}
