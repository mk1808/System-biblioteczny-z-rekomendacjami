import { AfterViewInit, Component, ElementRef, Input, OnInit,OnChanges, SimpleChanges  } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
//import * as $ from 'jquery';
declare let $: any;
declare let jQuery: any;
@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: SelectComponent
    }
  ]
})
export class SelectComponent implements OnInit, AfterViewInit, OnChanges, ControlValueAccessor {
  @Input() label: any;
  @Input() allowOwnValue: Boolean = false;
  @Input() options: any[]|null = [];
  @Input() getDisplayedValue:any;
  
  chooseValue=this.allowOwnValue?"chooseOrWriteVal":"chooseVal";
  value = ""
  onChange = (quantity: any) => { };
  onTouched = () => { };
  constructor(private element: ElementRef) { }

  writeValue(value: any): void {
    this.value = value;
  }
  registerOnChange(onChange: any): void {
    this.onChange = onChange;
  }
  registerOnTouched(onTouched: any): void {
    this.onTouched = onTouched;
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.options)
  }

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

  change(e:any) {
    this.onChange(e.target.value)
  }

}
