import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-multi-select',
  templateUrl: './multi-select.component.html',
  styleUrls: ['./multi-select.component.scss']
})
export class MultiSelectComponent implements OnInit {
  @Input() label:any;
  constructor() { }

  ngOnInit(): void {
  }

}
