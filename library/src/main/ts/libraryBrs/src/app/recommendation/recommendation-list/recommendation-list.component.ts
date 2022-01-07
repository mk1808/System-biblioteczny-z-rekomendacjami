import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recommendation-list',
  templateUrl: './recommendation-list.component.html',
  styleUrls: ['./recommendation-list.component.scss']
})
export class RecommendationListComponent implements OnInit {
  title="recommendations";
  rulesHeight="rulesHeight";
  textOpen="abc"
  textDropdown="options"
  currentRate = 6;
  no=0;
  book={id:0, photo:'https://picsum.photos/200/300', title:'Lorem ipsum dolor', author:'Dolores Gibson'}
  books=[this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook(),this.getBook() ];
  constructor() { }

  ngOnInit(): void {
  }
  test() {
    console.log('tets')
  }

  getBook(){
    this.no++;
    this.book.id=this.no;
    return this.book;
  }
}
