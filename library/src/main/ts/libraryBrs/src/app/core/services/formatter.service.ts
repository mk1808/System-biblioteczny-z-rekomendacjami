import { Injectable } from '@angular/core';

@Injectable()
export class FormatterService {

  constructor() { }

  displayList(options: any[]) {
    let wayOfDisplay = (option: any) => { return `${option.name} ${option.surname}, ` };
    return this.display(options, wayOfDisplay)
  }

  displayListNames(options: any[]) {
    let wayOfDisplay = (option: any) => { return `${option.name}, ` };
    return this.display(options, wayOfDisplay)
  }

  display(options: any[], wayOfDisplay: any) {

    let displayed = ""
    if (options && options.length > 0) {
      options.forEach(option => { displayed += wayOfDisplay(option) });
      displayed = displayed.substring(0, displayed.length - 2);
    } else {
      displayed = "-"
    }
    return displayed;
  }

  getBookCopyStatusName(status:any){
    let name="";
    switch(status){
      case 'IDLE': 
      case 'IDLE2':name = "Dostępna"; break;
      case 'BORROWED':name = "Wypożyczona"; break;
      case 'RESERVED':name = "Zarezerwowana"; break;
      case 'CANBORROW':name = "Dostępna"; break;
      default:"";
      
    }return name;
  }
}
