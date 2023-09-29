import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, Validator, AbstractControl, ValidationErrors } from '@angular/forms';

@Directive({
  selector: '[appDateBeforeCurrent][ngModel]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: DateBeforeCurrentDirective,
      multi: true,
    },
  ],
})
export class DateBeforeCurrentDirective implements Validator {
    
  validate(control: AbstractControl): ValidationErrors | null {
    const selectedDate = new Date(control.value);
    const currentDate = new Date();

    currentDate.setHours(0, 0, 0, 0);
    if (selectedDate <= currentDate) {
      return { dateBeforeCurrent: true };
    }

    return null;
  }
}
