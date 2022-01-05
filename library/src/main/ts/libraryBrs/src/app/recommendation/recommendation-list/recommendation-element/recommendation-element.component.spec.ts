import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendationElementComponent } from './recommendation-element.component';

describe('RecommendationElementComponent', () => {
  let component: RecommendationElementComponent;
  let fixture: ComponentFixture<RecommendationElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecommendationElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendationElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
