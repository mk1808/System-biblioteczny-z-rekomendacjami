import { Injectable, Inject } from '@angular/core';
import { I18NextPipe, I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';

@Injectable({
  providedIn: null//'root'
})
export class I18nService {

  constructor(@Inject(I18NEXT_SERVICE) private i18NextService: ITranslationService,
    private i18nextPipe: I18NextPipe) { }

  getTranslation(key: string) {
    return this.i18NextService.getResource("pl", "translation", key, "");
  }

  getTranslationWithParams(key: string, params: Object) {
    return this.i18nextPipe.transform(key, params);
  }

}
