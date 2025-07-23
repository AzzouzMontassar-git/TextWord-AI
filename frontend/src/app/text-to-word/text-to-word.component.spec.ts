import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextToWordComponent } from './text-to-word.component';

describe('TextToWordComponent', () => {
  let component: TextToWordComponent;
  let fixture: ComponentFixture<TextToWordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TextToWordComponent]
    });
    fixture = TestBed.createComponent(TextToWordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
