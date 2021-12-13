import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent implements OnInit {
  @Input() iconClass="";
  @Input() text="";
  @Input() btnClass="";

  constructor() { }

  ngOnInit(): void {
  }

}
