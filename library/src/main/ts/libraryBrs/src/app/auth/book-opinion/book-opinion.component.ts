import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-book-opinion',
  templateUrl: './book-opinion.component.html',
  styleUrls: ['./book-opinion.component.scss']
})
export class BookOpinionComponent implements OnInit {

  title = "My modal title"
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
