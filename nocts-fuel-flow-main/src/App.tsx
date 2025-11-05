// src/App.tsx (or main routes file)
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "@/pages/LoginPage";
import AdminNavigationPage from "@/pages/AdminNavigationPage";
import StaffNavigationPage from "@/pages/StaffNavigationPage";
import MonthlyReportPage from "@/pages/MonthlyReportPage";
import FuelActivationPage from "@/pages/FuelActivationPage";
import ApprovalsPage from "@/pages/ApprovalsPage"; // we'll create later (or you can stub it)
import { authService } from "@/services/authService";

const RequireAuth = ({ children }: { children: JSX.Element }) => {
  return authService.isLoggedIn() ? children : <Navigate to="/" replace />;
};

const RequireRole = ({ role, children }: { role: "admin" | "staff"; children: JSX.Element }) => {
  const r = authService.getRole();
  return r === role ? children : <Navigate to="/" replace />;
};

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />

        {/* Admin routes */}
        <Route
          path="/admin"
          element={
            <RequireAuth>
              <RequireRole role="admin">
                <AdminNavigationPage />
              </RequireRole>
            </RequireAuth>
          }
        />

        <Route
          path="/approvals"
          element={
            <RequireAuth>
              <RequireRole role="admin">
                <ApprovalsPage />
              </RequireRole>
            </RequireAuth>
          }
        />

        <Route
          path="/monthly-report"
          element={
            // both roles can view monthly report, but require login
            <RequireAuth>
              <MonthlyReportPage />
            </RequireAuth>
          }
        />

        {/* Staff routes */}
        <Route
          path="/staff"
          element={
            <RequireAuth>
              <RequireRole role="staff">
                <StaffNavigationPage />
              </RequireRole>
            </RequireAuth>
          }
        />

        <Route
          path="/dashboard"
          element={
            <RequireAuth>
              <RequireRole role="staff">
                <FuelActivationPage />
              </RequireRole>
            </RequireAuth>
          }
        />

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}
