import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-recommendation-element',
  templateUrl: './recommendation-element.component.html',
  styleUrls: ['./recommendation-element.component.scss']
})
export class RecommendationElementComponent implements OnInit, OnChanges {
  textDropdown="options"
  @Input() bookProp:any;
  @Input() authors:any;
  constructor() { }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.authors)
    console.log(this.bookProp)
  }

  ngOnInit(): void {
  }
  test() {
    console.log('tets')
  }

}
