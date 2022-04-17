import { Component, Input, OnChanges, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel-element',
  templateUrl: './carousel-element.component.html',
  styleUrls: ['./carousel-element.component.scss']
})
export class CarouselElementComponent implements OnInit, OnChanges {
  @Input() bookProp:any;
  @Input() authors:any;
  book={id:0, photo:'', title:'', author:''}
  constructor() { }

  ngOnInit(): void {
  }
  ngOnChanges(){
    this.book=this.bookProp;
  }

}
