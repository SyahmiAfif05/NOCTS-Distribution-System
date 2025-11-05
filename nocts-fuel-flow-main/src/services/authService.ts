// src/services/authService.ts
export type Role = "admin" | "staff";

export interface Session {
  token: string;
  role: Role;
  name: string;
  staffId: string;
}

const STORAGE_KEY = "nocts_session";

// ✅ Mock user database (adjust these to your real accounts later)
const mockUsers = [
  {
    staffId: "ADMIN001",
    password: "admin123",
    name: "John Doe",
    role: "admin" as Role,
  },
  {
    staffId: "STAFF001",
    password: "staff123",
    name: "Ahmad Fuel Guy",
    role: "staff" as Role,
  }
];

export const authService = {

  // ✅ mock login function
  async login(staffId: string, password: string) {
    await new Promise((resolve) => setTimeout(resolve, 1000)); // simulate delay

    const user = mockUsers.find(
      (u) => u.staffId === staffId && u.password === password
    );

    if (!user) {
      return { success: false, message: "Invalid Staff ID or Password" };
    }

    const session: Session = {
      token: `token_${user.staffId}_${Date.now()}`,
      role: user.role,
      name: user.name,
      staffId: user.staffId,
    };

    authService.loginLocal(session);

    return {
      success: true,
      user: session,
    };
  },

  loginLocal(session: Session) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(session));
  },

  logout() {
    localStorage.removeItem(STORAGE_KEY);
  },

  getSession(): Session | null {
    const raw = localStorage.getItem(STORAGE_KEY);
    return raw ? (JSON.parse(raw) as Session) : null;
  },

  getToken(): string | null {
    const s = authService.getSession();
    return s?.token ?? null;
  },

  getRole(): Role | null {
    const s = authService.getSession();
    return s?.role ?? null;
  },

  isLoggedIn(): boolean {
    return !!authService.getToken();
  },
};
