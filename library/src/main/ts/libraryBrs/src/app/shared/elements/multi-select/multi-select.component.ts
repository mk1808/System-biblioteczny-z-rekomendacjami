import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
declare let $: any;
declare let jQuery: any;
@Component({
  selector: 'app-multi-select',
  templateUrl: './multi-select.component.html',
  styleUrls: ['./multi-select.component.scss']
})
export class MultiSelectComponent implements OnInit, AfterViewInit {
  @Input() label: any;
  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.loadJQuery();
  }

  loadJQuery() {
    var element = $("#" + this.label).find('.search.dropdown');
    element.dropdown();
  }

}
