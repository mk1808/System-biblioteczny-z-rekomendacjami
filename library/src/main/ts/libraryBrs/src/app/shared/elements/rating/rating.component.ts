import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit {

  @Input() ratingFormCtrl:any;
  currentRate = 6;
  constructor() { }

  ngOnInit(): void {
    
  }

}
