import { FormControl, Validators } from '@angular/forms';

export class Luv2ShopValidator {
  static notOnlyWhitespace(control: FormControl): Validators {
    if (control.value != null && control.value.trim().length === 0) {
      return { notOnlyWhitespace: true };
    }
    return null;
  }
}
