import { AfterViewInit, Component, ElementRef, Input, OnInit } from '@angular/core';
//import * as $ from 'jquery';
declare let $: any;
declare let jQuery: any;
@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit, AfterViewInit {
  @Input() label: any;
  @Input() allowOwnValue: Boolean = false;
  constructor(private element: ElementRef) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {

    //let elements = this.element.nativeElement.querySelectorAll('.ui.dropdown');
    //    setTimeout(this.loadJQuery);
    this.loadJQuery();
  }


  loadJQuery() {
    /*debugger
    let select:any = $('.search.dropdown')[0];
     select.dropdown();*/
    var element = $("#" + this.label).find('.search.dropdown');
    element.dropdown();
    if (this.allowOwnValue) {
      var input = $("#" + this.label).find('input');
      input.on("keyup", (e: any) => {
        var newValue = e.target.value;
        var option = $("#" + this.label).find('[data-value="newValue"]')
        option.text(`Nowa wartość: ${newValue}`);
        option.attr(`data-text`, newValue)

        var e: any = jQuery.Event("input")
        input.trigger(e)
      })
    }
  }

}
