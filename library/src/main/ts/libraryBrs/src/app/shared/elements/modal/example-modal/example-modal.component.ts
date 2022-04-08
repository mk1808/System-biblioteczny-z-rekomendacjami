import { Component, Input, OnChanges, OnInit, SimpleChanges, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-example-modal',
  templateUrl: './example-modal.component.html',
  styleUrls: ['./example-modal.component.scss']
})
export class ExampleModalComponent implements OnInit, OnChanges{
  @Input() templateFooSelector?: TemplateRef<any>|null;
  templateFooSelector2:TemplateRef<any>;
  title = "My modal title"
  constructor() {let n;
    if(this.templateFooSelector!=null){
      let n:TemplateRef<any> = this.templateFooSelector;
      this.templateFooSelector2=n;
    } }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.templateFooSelector)
    let n;
    if(this.templateFooSelector!=null){
      let n:TemplateRef<any> = this.templateFooSelector;
      this.templateFooSelector2=n;
    }
    
  }
  ngOnInit(): void {
    console.log(this.templateFooSelector)
    let n;
    if(this.templateFooSelector!=null){
      let n:TemplateRef<any> = this.templateFooSelector;
      this.templateFooSelector2=n;
    }
  }

  onCancel=()=>{
    console.log("cnacel")
  }

  onConfirm=()=>{
    console.log("confirm")
  }

}
