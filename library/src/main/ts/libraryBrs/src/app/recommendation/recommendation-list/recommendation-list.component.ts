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
  constructor() { }

  ngOnInit(): void {
  }
  test() {
    console.log('tets')
  }
}
