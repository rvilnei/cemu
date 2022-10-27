import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendenciaNewComponent } from './pendencia-new.component';

describe('PendenciaNewComponent', () => {
  let component: PendenciaNewComponent;
  let fixture: ComponentFixture<PendenciaNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendenciaNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendenciaNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
