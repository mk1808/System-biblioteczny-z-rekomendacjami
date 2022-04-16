import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { I18nService } from 'src/app/core/services/i18n.service';

@Component({
  selector: 'app-textarea',
  templateUrl: './textarea.component.html',
  styleUrls: ['./textarea.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: TextareaComponent
    }
  ]
})
export class TextareaComponent implements OnInit, OnChanges, ControlValueAccessor {
  @Input() label: any;

  labelTranslated = '';
  value = "";
  onChange = (quantity: any) => { };
  onTouched = () => { };
  constructor(private i18nService: I18nService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.labelTranslated = this.i18nService.getTranslation(this.label);

  }
  writeValue(value: any): void {
    this.value = value;
  }
  registerOnChange(onChange: any): void {
    this.onChange = onChange;
  }
  registerOnTouched(onTouched: any): void {
    this.onTouched = onTouched;
  }

  ngOnInit(): void {
    this.labelTranslated = this.i18nService.getTranslation(this.label);
  }

  change() {
    this.onChange(this.value)
  }

}
