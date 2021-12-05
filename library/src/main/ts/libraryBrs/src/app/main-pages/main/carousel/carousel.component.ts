import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  no=0;
  properties = { height: 500, cells: 5, areArrowsOutside: true };
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