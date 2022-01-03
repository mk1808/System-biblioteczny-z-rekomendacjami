import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookSurveyElementComponent } from './book-survey-element.component';

describe('BookSurveyElementComponent', () => {
  let component: BookSurveyElementComponent;
  let fixture: ComponentFixture<BookSurveyElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookSurveyElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookSurveyElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
