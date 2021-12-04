import { APP_INITIALIZER, LOCALE_ID } from '@angular/core';
import { I18NEXT_SERVICE, I18NextModule, I18NextLoadResult, ITranslationService, defaultInterpolationFormat } from 'angular-i18next';
import LocizeApi from 'i18next-locize-backend';
import LanguageDetector from 'i18next-browser-languagedetector';
import translations from '../../translations/translations';

const i18nextOptions = {
    debug: true,
    fallbackLng: 'en',
    resources: translations,
    interpolation: {
        format: I18NextModule.interpolationFormat(defaultInterpolationFormat)
    }
};

export function appInit(i18next: ITranslationService) {
    return () => {
        let promise: Promise<I18NextLoadResult> = i18next
            .use(LocizeApi)
            .use<any>(LanguageDetector)
            .init(i18nextOptions);
        return promise;
    };
}

export function localeIdFactory(i18next: ITranslationService) {
    return i18next.language;
}

export const I18N_PROVIDERS = [
    {
        provide: APP_INITIALIZER,
        useFactory: appInit,
        deps: [I18NEXT_SERVICE],
        multi: true
    },
    {
        provide: LOCALE_ID,
        deps: [I18NEXT_SERVICE],
        useFactory: localeIdFactory
    },
];