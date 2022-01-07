import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recommendation-element',
  templateUrl: './recommendation-element.component.html',
  styleUrls: ['./recommendation-element.component.scss']
})
export class RecommendationElementComponent implements OnInit {
  textDropdown="options"
  constructor() { }

  ngOnInit(): void {
  }
  test() {
    console.log('tets')
  }

}
