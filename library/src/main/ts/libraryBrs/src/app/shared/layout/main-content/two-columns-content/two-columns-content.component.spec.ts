import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TwoColumnsContentComponent } from './two-columns-content.component';

describe('TwoColumnsContentComponent', () => {
  let component: TwoColumnsContentComponent;
  let fixture: ComponentFixture<TwoColumnsContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TwoColumnsContentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TwoColumnsContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
