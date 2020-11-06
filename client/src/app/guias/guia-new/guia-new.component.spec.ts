import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuiaNewComponent } from './guia-new.component';

describe('GuiaNewComponent', () => {
  let component: GuiaNewComponent;
  let fixture: ComponentFixture<GuiaNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuiaNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuiaNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
