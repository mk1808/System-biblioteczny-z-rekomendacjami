import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-example-modal',
  templateUrl: './example-modal.component.html',
  styleUrls: ['./example-modal.component.scss']
})
export class ExampleModalComponent implements OnInit {
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
