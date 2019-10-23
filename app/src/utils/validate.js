export const loginRules = {
  username: [{ validate: (val) => !!val, message: 'Username is required' },
    { validate: (val) => val.length >= 3, message: 'Username is too short' }],
  password: [{ validate: (val) => !!val, message: 'Password is required' },
    { validate: (val) => val.length >= 3 && val.length <= 18, message: 'Length of password needs to be from 3 to 18' }],
  agree: [{ validate: (val) => !!val, message: 'Please check the agreement' }]
}

export const editProfileRules = {
  name: [{ validate: (val) => !!val, message: 'Name is required' },
    { validate: (val) => val.length >= 3, message: 'Name is too short' }]
}

export function isvalidUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}

export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}
