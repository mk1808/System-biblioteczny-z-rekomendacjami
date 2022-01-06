import { Component, Input, OnInit } from '@angular/core';
declare let $ : any;

@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.scss']
})
export class DropdownComponent implements OnInit {
  @Input() text="";
  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.loadJQuery();
  }

  loadJQuery() {
    $('.ui.dropdown').dropdown();
  }

}
