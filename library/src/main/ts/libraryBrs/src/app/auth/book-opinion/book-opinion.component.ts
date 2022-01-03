import { AfterViewInit, Component, OnInit } from '@angular/core';
declare let $ : any;
@Component({
  selector: 'app-book-opinion',
  templateUrl: './book-opinion.component.html',
  styleUrls: ['./book-opinion.component.scss']
})
export class BookOpinionComponent implements OnInit{

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

 



