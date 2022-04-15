import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-rating-modal',
  templateUrl: './rating-modal.component.html',
  styleUrls: ['./rating-modal.component.scss']
})
export class RatingModalComponent implements OnInit {

  title = "My modal title"
  labelKeyword = "Słowa kluczowe"
  labelSubject = "Tematyka"
  currentRate = 6;
  constructor() { }
  ngOnInit(): void {
  }

  onCancel=()=>{
    console.log("cnacel")
  }

  onConfirm=()=>{
    console.log("confirm")
  }

}
