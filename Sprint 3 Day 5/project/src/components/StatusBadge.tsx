import React from 'react';
import classNames from 'classnames';
import { CheckCircle, AlertCircle, XCircle } from 'lucide-react';
import '../styles/StatusBadge.css';

interface StatusBadgeProps {
  status: 'success' | 'error' | 'warning';
  label?: string;
}

const StatusBadge: React.FC<StatusBadgeProps> = ({ status, label }) => {
  const badgeClasses = classNames('status-badge', {
    'status-success': status === 'success',
    'status-error': status === 'error',
    'status-warning': status === 'warning'
  });

  const getIcon = () => {
    switch (status) {
      case 'success':
        return <CheckCircle size={16} />;
      case 'error':
        return <XCircle size={16} />;
      case 'warning':
        return <AlertCircle size={16} />;
      default:
        return null;
    }
  };

  const getLabel = () => {
    if (label) return label;
    return status.charAt(0).toUpperCase() + status.slice(1);
  };

  return (
    <span className={badgeClasses}>
      {getIcon()}
      {getLabel()}
    </span>
  );
};

export default StatusBadge;