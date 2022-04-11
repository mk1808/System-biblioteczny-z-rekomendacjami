import { AfterViewInit, Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ControlValueAccessor } from '@angular/forms';
declare let $: any;
declare let jQuery: any;
@Component({
  selector: 'app-multi-select',
  templateUrl: './multi-select.component.html',
  styleUrls: ['./multi-select.component.scss']
})
export class MultiSelectComponent implements OnInit, AfterViewInit, OnChanges, ControlValueAccessor {
  @Input() label: any;
  @Input() allowOwnValue: Boolean = true;
  @Input() options: any[]|null = [];
  @Input() getDisplayedValue:any;

  chooseValue=this.allowOwnValue?"chooseOrWriteVal":"chooseVal";
  value: any[] = []
  newValue = "newValue1"

  constructor(private element: ElementRef) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.loadJQuery();
  }

  onChange = (quantity: any) => { };
  onTouched = () => { };

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
    if (this.options!=null) {this.options.unshift({id:0, name:"Wybierz"})}
    console.log(this.options)
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

    setTimeout(()=>{
    //e.target.parentElement.querySelectorAll("a")[0].dataset.value
    const existingValues:any[] = [];
    e.target.parentElement.querySelectorAll("a").forEach((option:any) => {
      const optionValue = option.dataset.value;
      if(optionValue !== "newValue") {
        if(!this.value.includes(optionValue)){
          this.value.push(optionValue);
        } 
        
      } else {
        var prevValue = option.dataset.value
        const removeMe = () =>{
          $(option).off("click", removeMe )
          //$(option).remove();
          this.value.splice(this.value.indexOf(option.dataset.value), 1);
          console.log(this.value)
          option.dataset.value = prevValue                                                                                                                                           
        }
        $(option).on( "click", removeMe )                                                                                                                                                     
        option.dataset.value = option.innerText;
        this.value.push(option.innerText);
      }
      existingValues.push(option.dataset.value)
    });
    var deletedOptionIndex = null;                                                                                                                                 
    for (let index = 0; index < this.value.length; index++) {
      if(!existingValues.includes(this.value[index])){
        deletedOptionIndex = index;
      }
    }                                                                                               
    if(deletedOptionIndex !== null){
      this.value.splice(deletedOptionIndex, 1);
    }

    console.log(this.value)

    this.onChange(this.value)

    }, 10)
  }

}
